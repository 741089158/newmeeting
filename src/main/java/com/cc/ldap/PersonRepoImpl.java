package com.cc.ldap;

import java.util.List;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;

import com.cc.bean.Person;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

public class PersonRepoImpl implements IPersonRepo {

	private LdapTemplate ldapTemplate;

	@Override
	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	@Override
	public List<String> getAllPersonNames() {
		return ldapTemplate.search(query().where("objectClass").is("user"),
				(AttributesMapper<String>) attrs -> (String) attrs.get("name").get());
	}

	@Override
	public List<Person> getAllPersons() {
		return ldapTemplate.search(query().where("objectClass").is("person"), new PersonAttributesMapper());
	}



}
