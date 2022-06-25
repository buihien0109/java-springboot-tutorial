package vn.techmaster.jpatutorial.id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpatutorial.id.id_sequence_generate.UserSequenceGenerate;
import vn.techmaster.jpatutorial.id.id_sequence_generate.UserSequenceGenerateRepository;

@SpringBootTest
public class UserSequenceGenerateTest {

    @Autowired
    private UserSequenceGenerateRepository userSequenceGenerateRepository;

    @Test
    void saveListUserSequenceGenerate() {
        for (int i = 0; i < 5; i++) {
            UserSequenceGenerate userSequenceGenerate = new UserSequenceGenerate();
            userSequenceGenerateRepository.save(userSequenceGenerate);
        }
    }

    @Test
    void saveSingleUserSequenceGenerate() {
        UserSequenceGenerate userSequenceGenerate = new UserSequenceGenerate();
        userSequenceGenerateRepository.save(userSequenceGenerate);
    }
}
