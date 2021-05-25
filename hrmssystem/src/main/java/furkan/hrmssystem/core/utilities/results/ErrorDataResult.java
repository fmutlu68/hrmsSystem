package furkan.hrmssystem.core.utilities.results;

public class ErrorDataResult<TData> extends DataResult<TData>{
    public ErrorDataResult(TData tData, String message) {
        super(tData, message, false);
    }

    public ErrorDataResult(TData tData) {
        super(tData, false);
    }

    public ErrorDataResult(String message){
        super(null, message, false);
    }

    public ErrorDataResult(){
        super(null, false);
    }
}
