// 1401. Circle and Rectangle Overlapping

public class CircleandRectangleOverlapping {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find closest point (closestX, closestY) on/inside rectangle to circle center
        int closestX = Math.max(x1, Math.min(x2, xCenter));
        int closestY = Math.max(y1, Math.min(y2, yCenter));

        // Calculate delta vector components
        int dx = closestX - xCenter;
        int dy = closestY - yCenter;

        // Check if squared distance is within squared radius
        return (dx * dx + dy * dy) <= (radius * radius);
    }
}
