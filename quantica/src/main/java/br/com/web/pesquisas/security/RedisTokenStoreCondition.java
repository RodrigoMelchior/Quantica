package br.com.web.pesquisas.security;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class RedisTokenStoreCondition implements Condition{

  public static final String REDIS_TOKEN_STORAGE_PROFILE = "redisTokenStorage";	
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
	  

          String[] profiles = context.getEnvironment().getActiveProfiles();  
          for (String profile : profiles) {
        	  if (profile.equalsIgnoreCase(REDIS_TOKEN_STORAGE_PROFILE)){
        		  return true;
        	  }
          }
	  
    return false;
  }
}