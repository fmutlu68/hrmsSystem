package furkan.hrmssystem.core.utilities.results;

public class SuccessDataResult<TData> extends DataResult<TData>{
    public SuccessDataResult(TData tData, String message) {
        super(tData, message, true);
    }

    public SuccessDataResult(TData tData) {
        super(tData, true);
    }

    public SuccessDataResult(String message){
        super(null,message,true);
    }
    public SuccessDataResult(){
        super(null,true);
    }
}
