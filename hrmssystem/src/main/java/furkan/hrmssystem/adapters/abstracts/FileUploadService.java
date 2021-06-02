package furkan.hrmssystem.adapters.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface FileUploadService {
    DataResult<Map> saveFile(MultipartFile file);
}
