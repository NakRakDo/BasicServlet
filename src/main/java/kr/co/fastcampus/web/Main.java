package kr.co.fastcampus.web;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

// 외장톰캣의 경우 도메인 뒤에 jar 프로젝트 이름이 붙고 그뒤에 루트 경로가 적용되는데
// 내장톰캣의 경우 /가 기존 루트이다. 헷갈리네
public class Main {
	public static void main(String[] args) throws LifecycleException {
		String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");

        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());
        
        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work -> 서블릿  3.0 이상부터는 web.xml이 필요없다.
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);
        
        tomcat.getConnector(); // tomcat 9 이상부터는 default 로 설정이 안되어잇는 부분이라서 직접 해준다.
        tomcat.start();
        tomcat.getServer().await();
	}
}
