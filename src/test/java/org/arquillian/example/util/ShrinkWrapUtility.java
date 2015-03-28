package org.arquillian.example.util;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class ShrinkWrapUtility {

	public static JavaArchive getJavaArchive() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
		jar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		jar.addAsManifestResource("persistence-h2.xml", "persistence.xml");
		return jar;
	}

	public static WebArchive getWebArchive() {
		WebArchive war = ShrinkWrap.create(WebArchive.class);
		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		// war.addAsResource("META-INF/orm.xml", "META-INF/orm.xml");
		war.addAsResource("persistence-h2.xml", "META-INF/persistence.xml");
		war.addAsResource("log4j2.xml", "log4j2.xml");
		war.addAsResource("log4j.xml", "log4j.xml");
		return war;
	}
}