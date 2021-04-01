package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.lang.reflect.Array;
import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution = new ArrayList<>();
    private double timeSpent;
    private int numStatesExplored = 0;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        ExtrinsicMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
        List<Vertex> visited = new ArrayList<>();
        Map<Vertex, Double> distTo = new HashMap<>();
        Map<Vertex, Vertex> edgeTo = new HashMap<>();

        pq.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);

        while (pq.size() != 0) {
            if (sw.elapsedTime() >= timeout) {
                outcome = SolverOutcome.TIMEOUT;
                timeSpent = sw.elapsedTime();
                return;
            }

            Vertex v = pq.removeSmallest();
            visited.add(v);

            if (v.equals(end)) {
                Vertex tmp = end;
                while (!tmp.equals(start)) {
                    solution.add(tmp);
                    tmp = edgeTo.get(tmp);
                }
                solution.add(start);

                List<Vertex> reverse = new ArrayList<>();
                for (int i = solution.size() - 1; i >= 0; i--) {
                    reverse.add(solution.get(i));
                }
                solution = reverse;

                solutionWeight = distTo.get(end);
                outcome = SolverOutcome.SOLVED;
                timeSpent = sw.elapsedTime();
                return;
            }

            numStatesExplored += 1;
            for (WeightedEdge<Vertex> edge : input.neighbors(v)) {
                Vertex from = edge.from();
                Vertex to = edge.to();
                double w = edge.weight();
                if (!visited.contains(to)) {
                    double dist = distTo.get(from) + w;
                    if (!pq.contains(to)) {
                        pq.add(to, dist + input.estimatedDistanceToGoal(to, end));
                        distTo.put(to, dist);
                        edgeTo.put(to, from);
                    } else {
                        if (dist < distTo.get(to)) {
                            pq.changePriority(to, dist + input.estimatedDistanceToGoal(to, end));
                            distTo.put(to, dist);
                            edgeTo.put(to, from);
                        }
                    }
                }
            }
        }
        outcome = SolverOutcome.UNSOLVABLE;
        timeSpent = sw.elapsedTime();
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
