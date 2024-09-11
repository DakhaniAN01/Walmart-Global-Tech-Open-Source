import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap {
    private final int numberOfChildren; 
    private final List<Integer> heap; 

    public PowerOfTwoMaxHeap(int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative.");
        }
        this.numberOfChildren = (int) Math.pow(2, exponent); 
        this.heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value); 
        heapifyUp(heap.size() - 1); 
    }

    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        int max = heap.get(0); 
        int lastValue = heap.remove(heap.size() - 1); 

        if (!heap.isEmpty()) {
            heap.set(0, lastValue); 
            heapifyDown(0); 
        }

        return max;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / numberOfChildren;

        while (index > 0 && heap.get(parentIndex) < heap.get(index)) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / numberOfChildren;
        }
    }

    private void heapifyDown(int index) {
        int largest = index;

        while (true) {
            int leftMostChildIndex = numberOfChildren * index + 1;

            for (int i = 0; i < numberOfChildren; i++) {
                int currentChildIndex = leftMostChildIndex + i;
                if (currentChildIndex < heap.size() && heap.get(currentChildIndex) > heap.get(largest)) {
                    largest = currentChildIndex;
                }
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void printHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2);
        heap.insert(10);
        heap.insert(20);
        heap.insert(15);
        heap.insert(30);
        heap.insert(5);
        heap.insert(25);

        heap.printHeap();
        System.out.println("Max: " + heap.popMax());
        heap.printHeap();
    }
}
