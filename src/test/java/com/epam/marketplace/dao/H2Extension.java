package com.epam.marketplace.dao;

import com.epam.marketplace.HibernateUtil;
import java.io.FileReader;
import java.sql.DriverManager;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class H2Extension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

  private static boolean notInitialized = true;

  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {
    if (notInitialized) {
      Class.forName("org.h2.Driver");
      RunScript.execute(DriverManager.getConnection("jdbc:h2:mem:test"),
          new FileReader("src/test/resources/common_test_script.sql"));
      notInitialized = false;
      HibernateUtil.init();
    }
  }

  @Override
  public void close() throws Throwable {
    DriverManager.getConnection("jdbc:h2:mem:test").close();
  }
}
