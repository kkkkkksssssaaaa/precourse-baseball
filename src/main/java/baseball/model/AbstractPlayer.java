package baseball.model;

import nextstep.utils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractPlayer {

    protected final Integer startIdx = 1;
    protected final Integer endIdx = 9;
    protected final Integer maxSize = 3;

    public abstract List<Integer> getNumbers();
    public abstract Integer getSize();

    public int getMaxSize() {
        return this.maxSize;
    }

}
