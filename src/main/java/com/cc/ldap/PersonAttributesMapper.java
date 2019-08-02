package com.cc.ldap;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import com.cc.bean.Person;

public class PersonAttributesMapper implements AttributesMapper<Person> {

	@Override
	public Person mapFromAttributes(Attributes attrs) throws NamingException {
		Person person = new Person();
		person.setPersonName((String) attrs.get("OU=Users Org Chart,DC=xmc,DC=wh").get());
		person.setOrgId((String) attrs.get("ou").get());
		return person;
	}

}
