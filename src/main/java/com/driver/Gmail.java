package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }
    TreeMap<Date,String> receive_Mail=new TreeMap<>();
    TreeMap<Date,String>trash=new TreeMap<>();
    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(getInboxSize()==inboxCapacity)
        {
            //this loop will run only one time for oldest message put into trash
            for(Map.Entry<Date,String>entry:receive_Mail.entrySet())
            {
                trash.put(entry.getKey(),entry.getValue());

                break;
            }
        }
        else {
            receive_Mail.put(date,message);
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Map.Entry<Date,String>entry:receive_Mail.entrySet())
        {
            if(entry.getValue().equals(message))
            {
                trash.put(entry.getKey(),entry.getValue());
                receive_Mail.remove(message);
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        String returnmessage="";
        if(getInboxSize()==0)return null;
        else
        {
            int count=0;

            for(Map.Entry<Date,String>entry:receive_Mail.entrySet())
            {
                count++;
                if(count==receive_Mail.size()-1)
                {
                    returnmessage=returnmessage+entry.getValue();//last message is latesest message in treemap according to date
                    break;
                }
            }
        }
        return returnmessage;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        String returnmessage="";
        if(getInboxSize()==0)return null;
        else
        {
            int count=0;

            for(Map.Entry<Date,String>entry:receive_Mail.entrySet())
            {
                returnmessage=returnmessage+entry.getValue();//first message is oldest message in treemap according to date
                break;
            }
        }

        return returnmessage;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        if(start.compareTo(end)<0)count++;
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return receive_Mail.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
