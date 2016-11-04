package br.com.web.pesquisas.security;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MemoryTokenStoreCondition implements Condition{

  public static final String MEMORY_TOKEN_STORAGE_PROFILE = "memoryTokenStorage";	
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
	  

          String[] profiles = context.getEnvironment().getActiveProfiles();  
          for (String profile : profiles) {
        	  if (profile.equalsIgnoreCase(MEMORY_TOKEN_STORAGE_PROFILE)){
        		  return true;
        	  }
          }
	  
    return false;
  }
}