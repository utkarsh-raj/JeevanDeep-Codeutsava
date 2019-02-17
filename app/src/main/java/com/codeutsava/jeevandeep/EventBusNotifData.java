package com.codeutsava.jeevandeep;

import com.google.firebase.messaging.RemoteMessage;

public class EventBusNotifData {
    private RemoteMessage groupChatDetails;

    public EventBusNotifData(RemoteMessage groupChatDetails){
        this.groupChatDetails = groupChatDetails;
    }

    public RemoteMessage getGroupChatDetails(){
        return groupChatDetails;
    }

}