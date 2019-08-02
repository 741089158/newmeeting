package com.cc.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.stereotype.Service;

import com.cc.service.LdapService;

@Service
public class LdapServiceImpl implements LdapService {


 	private static String LDAP_URL = "LDAP://192.168.139.123:389"; // LDAP访问地址
	private static String adminName = "zhangsan@lin.com";// username@domain
	private static String adminPassword = "Zhang123";// password
//    private static String LDAP_URL = "ldap://10.216.106.100:389"; // LDAP访问地址
//    private static String adminName = "CiscoUC@xmc.wh";// username@domain
//    private static String adminPassword = "Wh123456";// password

    /**
     * M配置LDAP参数
     */
    private Hashtable<String, String> configure() {
        Hashtable<String, String> HashEnv = new Hashtable<>();
        HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别
        HashEnv.put(Context.SECURITY_PRINCIPAL, adminName); // AD User
        HashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword); // AD Password
        HashEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类
        HashEnv.put(Context.PROVIDER_URL, LDAP_URL);
        return HashEnv;
    }

    /**
     * M配置用户需要返回的属性
     */
    private SearchControls userMapper() {
        SearchControls searchCtls = new SearchControls(); // Create the search controls
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Specify the search scope
        String returnedAtts[] = {"url", //
                "name", // 名称
                "departmentNumber", //
                "mobile", // 手机号
                "department", // 部门
                "sAMAccountName", // 工号
                "mail" // 邮箱
        }; // 定制返回属性

        searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集
        return searchCtls;
    }

    private SearchControls groupMapper() {
        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); // 所有子节点
        String returnedAtts[] = {"ou"};
        searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集
        return searchCtls;
    }

    // 获取指定条件所有用户
    public List<Map<String, String>> queryAllUser(String name, String groupSign) {
        List<Map<String, String>> allData = new ArrayList<Map<String, String>>(); // 返回值容器
        try {
            LdapContext ctx = new InitialLdapContext(configure(), null);

            // TODO

            if (name == "" || name == null) {
                name = "*";
               // return null;
            } else {
                name = "*" + name + "*";
            }
            String searchFilter = "(&(objectClass=User)(|(name=" + name + ")(sAMAccountName=" + name + ")))"; // 过滤条件

            if (groupSign == null || groupSign.length() == 0) {
                groupSign = "OU=Users Org Chart,";
            }
            String searchBase = groupSign + "DC=lin,DC=com"; // 查询域，用户组

            NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, userMapper());
            while (answer.hasMoreElements()) {
                SearchResult sr = answer.next();
                Attributes Attrs = sr.getAttributes();
                allData.add(toMap(Attrs));
            }
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allData;
    }


//    public static void main(String[] args) {
//        List<Map<String, String>> maps =new LdapServiceImpl().queryAllUser("蔡", "");
//        System.out.println(maps.size());
//    }


    // 根据名称获取指定用户
    public Map<String, String> queryUserByName(String name) {
        Map<String, String> map = new HashMap<>();
        try {
            LdapContext ctx = new InitialLdapContext(configure(), null);
            String searchFilter = "(&(objectClass=User)(name=" + name + "))";
            String searchBase = "OU=Users Org Chart,DC=lin,DC=com";
            NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, userMapper());
            while (answer.hasMoreElements()) {
                SearchResult sr = answer.next();
                Attributes Attrs = sr.getAttributes();
                map = toMap(Attrs);
            }
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 根据工号获取指定用户
    public Map<String, String> queryUserByAccount(String sAMAccountName) {
        Map<String, String> map = new HashMap<>();
        try {
            LdapContext ctx = new InitialLdapContext(configure(), null);
            String searchFilter = "(&(objectClass=User)(sAMAccountName=" + sAMAccountName + "))";
            String searchBase = "OU=Users Org Chart,DC=lin,DC=com";
            NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, userMapper());
            while (answer.hasMoreElements()) {
                SearchResult sr = answer.next();
                Attributes Attrs = sr.getAttributes();
                map = toMap(Attrs);
            }
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    // 获取所有的用户组织
    public List<Map<String, String>> queryAllGroup() {
        List<Map<String, String>> temp = new ArrayList<>();
        try {
            LdapContext ctx = new InitialLdapContext(configure(), null);

            String searchFilter = "(&(objectClass=organizationalUnit)(ou=*))";
            String searchBase = "OU=YMTC,OU=Users Org Chart,DC=lin,DC=com";

            NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, groupMapper());
            while (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.next();
                Attributes Attrs = sr.getAttributes(); // 属性集合
                temp.add(toMap(Attrs));
            }
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    // Attributes To Map
    private Map<String, String> toMap(Attributes Attrs) {
        Map<String, String> map = new HashMap<>(); // oneUser
        if (Attrs != null) {
            try {
                for (NamingEnumeration<? extends Attribute> ne = Attrs.getAll(); ne.hasMore(); ) {
                    Attribute Attr = (Attribute) ne.next();
                    // 读取属性值
                    NamingEnumeration<?> values = Attr.getAll();
                    if (values != null) {
                        while (values.hasMoreElements()) {
                            String enumId = Attr.getID();
                            String enumValue = values.nextElement().toString();
                            map.put(enumId, enumValue);
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Throw Exception : " + e);
            }
        }
        return map;
    }

    // 分页操作
    public List<Map<String, String>> doPage(Integer page, Integer size, List<Map<String, String>> allData) {
        page = (page == null || page < 1) ? 1 : page; // 校验页码
        size = (size == null || size < 1) ? 10 : size; // 校验分页大小
        int skip = (page - 1) * size; // 获取开始下标
        if (allData == null || allData.size() == 0 || allData.size() < skip) {
            return new ArrayList<>(); // 数据不足时返回空集合
        }
        int exp = skip + size; // 期望结束下标
        int eIndex = exp > allData.size() ? allData.size() : exp; // 实际结束下标
        List<Map<String, String>> thePageData = allData.subList(skip, eIndex);
        return thePageData;
    }

    // 根据组名查询用户
    public List<Map<String, String>> queryUserByGroup(String group) {
        List<Map<String, Object>> list = queryOUList("OU=YMTC,OU=Users Org Chart,");
        Set<Map<String, Object>> set = new HashSet<>();
        // 组织集合
        List<Map<String, Object>> listNew = new ArrayList<Map<String, Object>>();
        set.addAll(list);
        listNew.addAll(set);
        List<Map<String, String>> user = null;
        for (Map<String, Object> map : listNew) {
            if (map.get("label").equals(group)) {
                user = getOuUser(map.get("key").toString());
            }
        }
        return user;
    }

    // 查询指定用户组下的用户
    private List<Map<String, String>> getOuUser(String groupSign) {
        List<Map<String, String>> allData = new ArrayList<Map<String, String>>();
        try {
            LdapContext ctx = new InitialLdapContext(configure(), null);
            SearchControls searchCtls = new SearchControls(); // Create the search controls
            searchCtls.setSearchScope(SearchControls.ONELEVEL_SCOPE); // 下一节点
            String returnedAtts[] = {"url", "name", "departmentNumber", "mobile", "department", "sAMAccountName",
                    "mail"}; // 定制返回属性
            searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集

            String searchFilter = "(&(objectClass=User)(name=*))";
            if (groupSign == null || groupSign.length() == 0) {
                groupSign = "OU=Users Org Chart,";
            }
            String searchBase = groupSign + "DC=lin,DC=com";

            NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, searchCtls);
            while (answer.hasMoreElements()) {
                SearchResult sr = answer.next();
                Attributes Attrs = sr.getAttributes();
                allData.add(toMap(Attrs));
            }
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allData;
    }

    private List<Map<String, Object>> resList = new ArrayList<>();

    private List<Map<String, Object>> queryOUList(String oukey) {
        List<Map<String, String>> temp = new ArrayList<>();
        try {
            LdapContext ctx = new InitialLdapContext(configure(), null);
            SearchControls searchCtls = new SearchControls();
            searchCtls.setSearchScope(SearchControls.ONELEVEL_SCOPE); // 下一节点
            String returnedAtts[] = {"ou"};
            searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集

            String searchFilter = "(&(objectClass=organizationalUnit)(ou=*))";
            String searchBase = oukey + "DC=lin,DC=com";

            NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, searchCtls);
            while (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.next();
                Attributes Attrs = sr.getAttributes(); // 属性集合
                temp.add(toMap(Attrs));
            }
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (temp.size() == 0) {
            return resList;
        }
        for (Map<String, String> o : temp) {
            Map<String, String> oMap = o;

            String str = oMap.get("ou");
            Map<String, Object> ch = new HashMap<>();
            ch.put("key", "OU=" + str + "," + oukey);
            ch.put("label", str);
            List<Map<String, Object>> qc = queryOUList("OU=" + str + "," + oukey);
            if (qc != null && qc.size() > 0) {
                resList.add(qc.get(0));
            }
            resList.add(ch);
        }
        return resList;
    }

}
