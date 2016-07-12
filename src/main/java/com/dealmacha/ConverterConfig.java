package com.dealmacha;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.util.MethodInvoker;

/**
 * This Class register all the custom converters to the spring conversion service
 *
 * @author sankar
 *
 */
@Configuration
public class ConverterConfig {
    // private final static Logger LOGGER = LoggerFactory.getLogger(ConverterConfig.class);

    @Autowired
    private ListableBeanFactory beanFactory;

    @PostConstruct
    public void init() {
        GenericConversionService service = beanFactory.getBean(Application.CONVERSION_SERVICE_BEANNAME, GenericConversionService.class);
        Collection<Converter> converters = BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory, Converter.class).values();

        // LOGGER.debug("Registering {} converters", converters.size());
        if (!converters.isEmpty()) {
            MethodInvoker invoker = new MethodInvoker();
            invoker.setTargetObject(service);
            invoker.setTargetMethod("getRequiredTypeInfo");
            invoker.setArguments(new Object[] { null, Converter.class });
            try {
                invoker.prepare();
            }
            catch (ClassNotFoundException | NoSuchMethodException e) {
                throw new UnsupportedOperationException("Unable to create MethodInvoker for 'getRequiredTypeInfo'", e);
            }

            for (Converter c : converters) {
                service.addConverter(c);
                invoker.setArguments(new Object[] { c, Converter.class });
                ResolvableType[] genericTypes = null;
                try {
                    genericTypes = (ResolvableType[]) invoker.invoke();
                }
                catch (InvocationTargetException | IllegalAccessException e) {
                    throw new UnsupportedOperationException("Unable to execute MethodInvoker for 'getRequiredTypeInfo'", e);
                }
                //  LOGGER.debug("Registered Converter [converter={}, source={}, target={}]", new Object[] { c, genericTypes[0],genericTypes[1] });
            }
        }

        // LOGGER.debug("Registering default converters");
        DefaultConversionService.addDefaultConverters(service);
    }
}