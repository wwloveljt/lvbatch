/**
 * 
 */

    var PushStomp = {};// Stomp.over(new SockJS('/crrmis/websock'));
    PushStomp.onEventNotify = function(event) {};
    PushStomp.stomp = null;
	PushStomp._connected = false;

	function OnUnReadMsgCountChanged(count) {
		if (Number(count) > 0)
			$(".unreadmsgs").text(count);
		else
			$(".unreadmsgs").text("");
	}
	
    $(document).ready(function() {

    	if (currentUserId > 0) {
    		
    		//var options = {protocols_whitelist: ["websocket", "xhr-streaming", "xdr-streaming", "xhr-polling", "xdr-polling", "iframe-htmlfile", "iframe-eventsource", "iframe-xhr-polling"], debug: true}; 
    		//var options = {transports: ["websocket", "xhr-polling"], debug: true};
    		//var options = {debug: true};
    		
    		var options;
    		if ((window.WebSocket)||(window.MozWebSocket)) {
    			options = {transports: ["websocket"], debug: false};
    		} else {
    			options = {transports: ["xhr-polling"], debug: true};
    		}
    		
    		var stomp = Stomp.over(new SockJS(WebsockUrl + "websock", undefined, options));
    		PushStomp.stomp = stomp;
    		
    		stomp.debug = function(log) {};
    		
    		stomp.connect({}, function(frame) {
		    	
    			PushStomp._connected = true;
		    	
		    	//console.log("websocket connected");
		    	
		    	stomp.subscribe('/queue/event', function(msg){
		    		//var event = JSON.parse(msg.body);
		            //console.log(event.id + ": " + event.name );
		    		PushStomp.onEventNotify(JSON.parse(msg.body));
		        });
		    	
		    	stomp.subscribe('/usr/queue/newmsgcnt', function(msg){
		            //console.log("/usr/queue/newmsgcnt : " + msg.body );
		            OnUnReadMsgCountChanged(msg.body);
		        });
		    	
		    });
		    
    		$.ajax({
    			url: WebsockUrl + "msgs/unreadcount",
    			type: 'GET', 
    			dataType: 'text',
    			contentType: 'application/text',
    			success: $.proxy( function( result ) {
    				OnUnReadMsgCountChanged(result);
    			}, this )
        	
        	});
    		
    	}
    	
    	
    });

    $(window).on('beforeunload', function(){
    	if (PushStomp.stomp != null) {
    		if ( PushStomp._connected )
    			PushStomp.stomp.disconnect();
    		PushStomp.stomp = null;
    	}
    	return undefined;
    });

	

