package com.zhanhongit.batch;

import java.io.File;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.zhanhongit.model.Person;

@Configuration
@EnableBatchProcessing
public class CsvBatchConfig {

	@Bean
	public ItemReader<Person> reader() throws Exception {
		FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
		String filePath="c:/person.csv";  
		File file = new java.io.File(filePath);
		FileSystemResource rest2 = new FileSystemResource(file);
	        
		reader.setResource(new ClassPathResource("person.csv"));
		reader.setEncoding("gbk");
	     reader.setResource(rest2);
		reader.setLineMapper(new DefaultLineMapper<Person>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "city", "area", "school", "grade", "bj", "name", "teacher",
								"teacher_tel", "patriarch", "patriarch_tel","zkzh", "kc","kd" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public Validator<Person> csvBeanValidator() {
		return new CsvBeanValidator<Person>();
	}

	@Bean
	public ItemProcessor<Person, Person> processor() {
		CsvItemProcessor processor = new CsvItemProcessor();
		processor.setValidator(csvBeanValidator());
		return processor;
	}

	@Bean
	public ItemWriter<Person> writer(javax.sql.DataSource dataSource) {
		JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		String sql = "insert into person (id ,city, area, school, grade, bj, name, teacher, teacher_tel, patriarch, patriarch_tel, zkzh, kc, kd) "
				+ "values(hibernate_sequence.nextval, :city, :area, :school, :grade, :bj, :name, :teacher, :teacher_tel, :patriarch, :patriarch_tel, :zkzh, :kc, :kd)";
		writer.setSql(sql);
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager ) throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(dataSource);
		jobRepositoryFactoryBean.setTransactionManager(transactionManager);
		jobRepositoryFactoryBean.setDatabaseType("H2");
		return  jobRepositoryFactoryBean.getObject();
	}

	@Bean
	public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager platformTransactionManager) throws Exception{
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository( jobRepository(dataSource, platformTransactionManager));		
		return jobLauncher;
	}
	
	@Bean
	public Job importJob(JobBuilderFactory jobs, Step step1){
		return jobs.get("importJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1)
				.end()
				.listener(csvJobListener())
				.build();
	}
	
	@Bean
	public Step step1 (StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer, ItemProcessor<Person, Person> processor){
		return  stepBuilderFactory
				.get("step1")
				.<Person, Person> chunk(65000)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	
	@Bean
	public CsvJobListener csvJobListener(){
		return new CsvJobListener();
	}
	
	
	
	
	
	
	
	
}
