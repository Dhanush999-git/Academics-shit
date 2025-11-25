package slidingwindow;
import java.util.*;

public class slidingwindow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Window size
        System.out.print("Enter the window size: ");
        int windowSize = sc.nextInt();

        // Number of frames to send
        System.out.print("Enter the number of frames to send: ");
        int totalFrames = sc.nextInt();

        int sent = 0; // frames sent counter
        int ack = 0; // acknowledgements received

        Random rand = new Random();

        while (ack < totalFrames) {
            // Send frames in current window
            int framesThisWindow = Math.min(windowSize, totalFrames - sent);
            System.out.println("\nSending frames: ");
            for (int i = 0; i < framesThisWindow; i++) {
                System.out.println("Frame " + (sent + i));
            }

            // Simulate acknowledgements
            boolean error = rand.nextBoolean(); // random success/failure

            if (!error) {
                // All frames acknowledged
                System.out.println("All " + framesThisWindow + " frames acknowledged!");
                sent += framesThisWindow;
                ack = sent;
            } else {
                // Simulate lost frame
                int lostFrame = sent + rand.nextInt(framesThisWindow);
                System.out.println("Frame " + lostFrame + " lost! Resending from that frame...");

                // Resend from lost frame
                sent = lostFrame;
                ack = lostFrame;
            }
        }

        System.out.println("\nAll " + totalFrames + " frames sent successfully!");
        sc.close();
    }
}
