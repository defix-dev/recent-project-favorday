package defix.favordayapp.mongoUtils;

import defix.favordayapp.services.post.exceptions.PostDoNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public class MongoRepoService<T extends UniqueObject, T1> {
    private static final int FIRST_ID = 1;
    protected final MongoRepository<T, T1> repository;

    public MongoRepoService(MongoRepository<T, T1> mongoRepo) {
        this.repository = mongoRepo;
    }

    public long getLastId() throws PostDoNotFoundException {
        List<T> posts = sortPostByIdWithDirection(Sort.Direction.DESC);
        return posts.stream().findFirst().orElseThrow(PostDoNotFoundException::new).getId();
    }

    public long getFirstId() throws PostDoNotFoundException {
        List<T> posts = sortPostByIdWithDirection(Sort.Direction.ASC);
        return posts.stream().findFirst().orElseThrow(PostDoNotFoundException::new).getId();
    }

    protected List<T> sortPostByIdWithDirection(Sort.Direction direction) {
        Pageable pageable = PageRequest.of(0, 1, direction, "_id");
        return repository.findAll(pageable).stream().toList();
    }

    protected long generateNextId() {
        try {
            return getLastId()+1;
        }
        catch (Exception e) {
            return FIRST_ID;
        }
    }
}
