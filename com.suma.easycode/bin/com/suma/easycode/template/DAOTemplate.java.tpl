
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
${poPath};
import com.sumavision.tetris.orm.dao.MetBaseDAO;

@RepositoryDefinition(domainClass = ${poName}.class, idClass = long.class)
public interface ${daoName} extends MetBaseDAO<${poName}>{

	
	
}
