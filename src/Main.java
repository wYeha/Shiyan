import mpi.MPI;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int[] message = new int[1];
        int tag = 0;
        message[0] = rank;

        if(rank%2==0){
            if(rank+1!=size) MPI.COMM_WORLD.Send(message, 0, 1, MPI.INT, rank+1, tag);
        }
        else {
            MPI.COMM_WORLD.Recv(message, 0, 1, MPI.INT, rank-1, tag);
            System.out.println("received: " + message[0]);
        }

        MPI.Finalize();
    }
}