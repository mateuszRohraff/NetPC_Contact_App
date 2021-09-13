package com.rohraff.netpccontactapp.mapper;

import com.rohraff.netpccontactapp.model.Contact;
import com.rohraff.netpccontactapp.model.ContactDao;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ContactMapper {

    @Insert("INSERT INTO CONTACTS (firstname, lastname, email, key, password, phoneNumber, dateOfBirth)  VALUES(#{firstname}, #{lastname}, #{email}, #{key}, #{password}, #{phoneNumber}, #{dateOfBirth})")
    @Options(useGeneratedKeys = true, keyProperty = "contactId")
    int insertContacts(ContactDao contact);

    @Select("SELECT * FROM CONTACTS")
    List<Contact> getContacts();

    @Delete("DELETE FROM CONTACTS WHERE contactId = #{contactId}")
    void deleteContact(int contactId);

    @Update("UPDATE CONTACTS SET firstname=#{firstname}, lastname=#{lastname}, email=#{email}, password=#{password}, phoneNumber=#{phoneNumber} where contactId =#{contactId}")
    void updateContacts(ContactDao contact);

    @Select("SELECT EMAIL FROM CONTACTS")
    List<String> getAllEmailAddresses();

    @Select("SELECT EMAIL FROM CONTACTS WHERE contactId = #{contactId}")
    String getEmail(int contactId);
}
