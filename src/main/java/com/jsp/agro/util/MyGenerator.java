package com.jsp.agro.util;

//import java.io.Serializable;
//import java.util.Properties;
//import java.util.stream.Stream;
//
//import org.hibernate.HibernateException;
//import org.hibernate.MappingException;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.Configurable;
//import org.hibernate.id.IdentifierGenerator;
//import org.hibernate.service.ServiceRegistry;
//import org.hibernate.type.Type;
//
//public class MyGenerator implements IdentifierGenerator, Configurable {
//	 private String prefix;
//
//	  @Override
//	  public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
//
//		  String query = String.format("select %s from %s", session.getEntityPersister(obj.getClass().getName(), obj)
//		              .getIdentifierPropertyName(),
//		            obj.getClass().getSimpleName());
//
//		        Stream<String> ids = session.createQuery(query).stream();
//
//		        Long max = ids.map(o -> o.replace(prefix + "-", ""))
//		          .mapToLong(Long::parseLong)
//		          .max()
//		          .orElse(0L);
//
//		        return prefix + "-" + (max + 1);
//		    }
//
//	  @Override
//	  public void configure(Type type, Properties properties, 
//	    ServiceRegistry serviceRegistry) throws MappingException {
//	      prefix = properties.getProperty("prefix");
//	  }
//}
import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class MyGenerator extends SequenceStyleGenerator {
	 public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
	    public static final String VALUE_PREFIX_DEFAULT = "";
	    private String valuePrefix;

	    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
	    public static final String NUMBER_FORMAT_DEFAULT = "%d";
	    private String numberFormat;

	    @Override
	    public Serializable generate(SharedSessionContractImplementor session,
	                                 Object object) throws HibernateException {
	        return valuePrefix + String.format(numberFormat, super.generate(session, object));
	    }

	    @Override
	    public void configure(Type type, Properties params,
	                          ServiceRegistry serviceRegistry) {
	        super.configure(LongType.INSTANCE, params, serviceRegistry);
	        valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER,
	                params, VALUE_PREFIX_DEFAULT);
	        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER,
	                params, NUMBER_FORMAT_DEFAULT);
	    }

}
