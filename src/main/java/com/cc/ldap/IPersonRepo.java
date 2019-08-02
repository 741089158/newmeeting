package com.cc.ldap;

import java.util.List;

import org.springframework.ldap.core.LdapTemplate;

import com.cc.bean.Person;

public interface IPersonRepo {

	void setLdapTemplate(LdapTemplate ldapTemplate);

	List<String> getAllPersonNames();

	List<Person> getAllPersons();

}
