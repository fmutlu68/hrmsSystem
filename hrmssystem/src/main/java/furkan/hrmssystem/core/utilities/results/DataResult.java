package furkan.hrmssystem.core.utilities.results;

public class DataResult<TData> extends Result{
    private TData data;
    public DataResult(TData data, String message, boolean success) {
        super(message, success);
        this.data = data;
    }

    public DataResult(TData data, boolean success) {
        super(success);
        this.data = data;
    }

    public TData getData() {
        return data;
    }
}
