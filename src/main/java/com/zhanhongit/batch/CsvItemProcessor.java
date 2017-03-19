package com.zhanhongit.batch;


import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import com.zhanhongit.model.Person;

public class  CsvItemProcessor extends ValidatingItemProcessor<Person> {
	@Override
	public Person process(Person item) throws ValidationException {
		super.process(item);
		if (item.getState() !=null){
			item.setState(true);
		}else{
			item.setState(false);
		}
		
		
	
		System.out.println(item.toString());
		return item;
	}
}

/*public class CsvItemProcessor implements ItemProcessor<Person, Person> {

	@Override
	public Person process(Person item) throws Exception {
		if (item.getState() !=null){
			item.setState(true);
		}else{
			item.setState(false);
		}
		
		//System.out.println(item.toString());
		return item;
	
	}

}
*/