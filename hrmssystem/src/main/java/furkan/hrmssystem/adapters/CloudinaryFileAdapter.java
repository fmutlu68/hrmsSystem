package furkan.hrmssystem.adapters;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import furkan.hrmssystem.adapters.abstracts.FileUploadService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.ErrorDataResult;
import furkan.hrmssystem.core.utilities.results.SuccessDataResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryFileAdapter implements FileUploadService {
    private Cloudinary cloudinary;

    @PostConstruct()
    private void initCloudinary() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "CLOUD_NAME",
                "api_key", "API_KEY",
                "api_secret", "API_SECRET"
        ));
    }
    @SuppressWarnings("rawtypes")
    @Override
    public DataResult<Map> saveFile(MultipartFile file) {
        Map uploadResult = null;
        try{
            uploadResult = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        }catch(IOException exception){
            return new ErrorDataResult<Map>("Hata Oluştu: " + exception.getLocalizedMessage());
        }
        if (uploadResult == null){
            return new ErrorDataResult<Map>(uploadResult, "Resim Eklenemedi: ");
        }
        return new SuccessDataResult<Map>(uploadResult, "Resim Ekleme Başarılı");
    }
}
