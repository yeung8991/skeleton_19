public class OffByN implements CharacterComparator {
    private int offNum;

    public OffByN(int N) {
        offNum = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == offNum || diff == -offNum) {
            return true;
        }
        return false;
    }
}
