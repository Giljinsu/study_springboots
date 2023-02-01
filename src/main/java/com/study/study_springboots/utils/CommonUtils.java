package com.study.study_springboots.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {
    public String getUniqueSequence() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    // "src/main/resources/static/files/" -> root directory + 상대경로
    public String getRelativeToAbsolutePath(String relativePath) {
        String relativePathWithSaparator = relativePath.replace("/", File.separator);
        // separator는 경로를 OS에 맞게 바꿔줌
        // 윈도우는 \ 리눅스는 /
        String absolutePath = new File(relativePathWithSaparator).getAbsolutePath() + File.separator; // 폴더의 경로를 가져옴
        // separator를 붙인 이유 뒤에 /가 없어짐
        return absolutePath;
    }
}
