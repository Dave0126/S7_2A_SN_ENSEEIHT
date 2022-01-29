package linda.search.parallel;
public enum Code {
    Request, // Request, UUID, String
    Value,   // Value, String
    Result,   // Result, UUID, String, Int
    Searcher, // Result, "done", UUID
}
