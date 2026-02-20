public class Div_Conquer {
    public static void mergeSort(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }
        int mid = si + (ei - si) / 2;
        mergeSort(arr, si, mid);
        mergeSort(arr, mid + 1, ei);
        merge(arr, si, mid, ei);
    }

    public static void merge(int arr[], int si, int mid, int ei) {
        int temp[] = new int[ei - si + 1];
        int i = si;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= ei) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
                k++;
            } else {
                temp[k] = arr[j];
                j++;
                k++;
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= ei) {
            temp[k++] = arr[j++];
        }
        for (k = 0, i = si; k < temp.length; k++, i++) {
            arr[i] = temp[k];
        }

    }

    public static void quickSort(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }
        // last element
        int pIdx = partition(arr, si, ei);
        quickSort(arr, si, pIdx - 1);
        quickSort(arr, pIdx + 1, ei);
    }

    public static int partition(int arr[], int si, int ei) {
        int pivot = arr[ei];
        int i = si - 1;
        for (int j = si; j < ei; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        i++;
        int temp = pivot;
        arr[ei] = arr[i];
        arr[i] = temp;
        return i;

    }

    public static int rotatedSorted_search(int arr[], int target, int si, int ei) {
        if (si > ei) {
            return -1;
        }
        int mid = si + (ei - si) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        // mid on L1
        if (arr[si] <= arr[mid]) {
            if (arr[si] <= target && target <= arr[mid]) {
                return rotatedSorted_search(arr, target, si, mid - 1);
            } else {
                return rotatedSorted_search(arr, target, mid + 1, ei);
            }
        }
        // mid on L2
        if (arr[mid] <= arr[ei]) {
            if (arr[mid] <= target && target <= arr[ei]) {
                return rotatedSorted_search(arr, target, mid + 1, ei);
            } else {
                return rotatedSorted_search(arr, target, si, mid - 1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = { 7,8,0,1,2,3,4 };
        // mergeSort(arr, 0, arr.length - 1);
        // quickSort(arr, 0, arr.length - 1);
        int tarIdx = rotatedSorted_search(arr, 7, 0, arr.length - 1);
        // for (int index = 0; index < arr.length; index++) {
        // System.out.print(arr[index] + " ");
        // }
        System.out.println(tarIdx);
    }
}
