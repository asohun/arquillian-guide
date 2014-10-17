package org.arquillian.example.util;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class ShrinkWrapUtility {

	public static JavaArchive getJavaArchive() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
		jar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		jar.addAsManifestResource("test-persistence.xml", "persistence.xml");
		return jar;
	}

	public static WebArchive getWebArchive() {
		WebArchive war = ShrinkWrap.create(WebArchive.class);
		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		// war.addAsResource("META-INF/orm.xml", "META-INF/orm.xml");
		war.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
		return war;
	}
}