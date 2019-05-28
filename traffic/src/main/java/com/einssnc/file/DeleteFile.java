package com.einssnc.file;

import java.io.File;

public class DeleteFile {

	public boolean start(String fullFileName) {

		File file = new File(fullFileName);

		if (file.exists()) {
			if (file.delete()) {
				System.out.println("파일삭제 성공");
				return true;
			} else {
				System.out.println("파일삭제 실패");
			}
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}

		return false;
	}
}
