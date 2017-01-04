package co.za.rightit.checks.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;

import co.za.rightit.checks.model.CheckConfig;
import co.za.rightit.checks.model.Node;

public class ChecksDataFactory {
	public static MongoCollection getChecksCollection() {
        Fongo fongo = new Fongo("mongo in-memory server");
        DB db = fongo.getDB("config");
        Jongo jongo = new Jongo(db);
        MongoCollection checks = jongo.getCollection("checks");
        checks.ensureIndex("{name:1}", "{unique: true}");
        return checks;
    }

    public static CheckConfig createCheckConfig() {
        return new CheckConfig().withName("MemcachedCheck").withNodes(createCheckNodeConfig());
    }

    @SuppressWarnings("serial")
    public static List<Node> createCheckNodeConfig() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node().withName("admin").withProperties(new HashMap<String, Object>() {
            {
                put("email1", "sysadmin@examle.com");
                put("mailServer", "127.0.0.1");
                put("contactsFile", "/path/to/contacts.ini");
                put("debug", Boolean.FALSE);
            }
        }));
        nodes.add(new Node().withName("hostname").withProperties(new HashMap<String, Object>() {
            {
                put("type", "TCPCheck");
                put("host", "127.0.0.1");
                put("port", "11211");
                put("timeout", 10);
                put("retries", 3);
                put("executeOnFailureHost", "hostname");
                put("executeOnFailureCommand", "service memcached restart");
                put("notifyEvery", 20);
                put("errorMessage", "Some error message");
            }
        }));
        return nodes;
    }
}
