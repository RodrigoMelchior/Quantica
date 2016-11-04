package br.com.web.pesquisas.repository.search;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.com.web.pesquisas.domain.User;

/**
 * Spring Data ElasticSearch repository for the User entity.
 */
public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {
}