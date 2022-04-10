package pro.sky.java.course3.work6.service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.java.course3.work6.model.Avatar;

import java.io.IOException;

public interface AvatarService {
    void uploadStudentAvatar(Long avatarId, MultipartFile avatarFile) throws IOException;

    Avatar findStudentAvatar(Long avatarId);
}
