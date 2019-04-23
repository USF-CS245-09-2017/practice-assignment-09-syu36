import java.util.NoSuchElementException;

public class BinaryHeap {
	private int[] data;
	private int size;

	public BinaryHeap() {
		data = new int[10];
		size = 0;
	}

	public void add(int item) {
		if (size == data.length - 1) {
			growArray();
		}

		data[size++] = item;

		int current = size - 1;
		int parent = (current - 1) / 2;

		while (data[current] < data[parent] && current != 0) {
			swap(data, current, parent);
			current = parent;
			parent = (current - 1) / 2;
		}

	}

	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private void growArray() {
		int[] temp = new int[data.length * 2];

		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}

		data = temp;
	}

	public int remove() {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		swap(data, size - 1, 0);

		size--;

		if (size > 0){
			shiftDown(0);
		}

		return data[size];
	}

	private void shiftDown(int index) {
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		int smaller;

		if (left < size && right < size) {
			smaller = data[left] < data[right] ? left : right;
		} else {
			smaller = index;
		}

		if (data[smaller] < data[index]) {
			swap(data, smaller, index);
			shiftDown(smaller);
		}
	}
}

