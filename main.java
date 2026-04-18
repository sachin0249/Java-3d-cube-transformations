import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.*;

class Cube3D implements GLEventListener {

    float angle = 0;

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glEnable(GL.GL_DEPTH_TEST);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        // Translation
        gl.glTranslatef(0.0f, 0.0f, -6.0f);

        // Rotation
        gl.glRotatef(angle, 1.0f, 1.0f, 0.0f);

        // Scaling
        gl.glScalef(1.2f, 1.2f, 1.2f);

        drawCube(gl);

        angle += 1.0f;
    }

    void drawCube(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS);

        // Front
        gl.glVertex3f(-1, -1, 1);
        gl.glVertex3f(1, -1, 1);
        gl.glVertex3f(1, 1, 1);
        gl.glVertex3f(-1, 1, 1);

        // Back
        gl.glVertex3f(-1, -1, -1);
        gl.glVertex3f(1, -1, -1);
        gl.glVertex3f(1, 1, -1);
        gl.glVertex3f(-1, 1, -1);

        // Top
        gl.glVertex3f(-1, 1, -1);
        gl.glVertex3f(1, 1, -1);
        gl.glVertex3f(1, 1, 1);
        gl.glVertex3f(-1, 1, 1);

        // Bottom
        gl.glVertex3f(-1, -1, -1);
        gl.glVertex3f(1, -1, -1);
        gl.glVertex3f(1, -1, 1);
        gl.glVertex3f(-1, -1, 1);

        // Right
        gl.glVertex3f(1, -1, -1);
        gl.glVertex3f(1, 1, -1);
        gl.glVertex3f(1, 1, 1);
        gl.glVertex3f(1, -1, 1);

        // Left
        gl.glVertex3f(-1, -1, -1);
        gl.glVertex3f(-1, 1, -1);
        gl.glVertex3f(-1, 1, 1);
        gl.glVertex3f(-1, -1, 1);

        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        if (height == 0) height = 1;

        float aspect = (float) width / height;

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        new GLU().gluPerspective(45.0f, aspect, 1.0, 100.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }
}

public class Cube3DDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("3D Cube Transformations");

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);

        Cube3D cube = new Cube3D();
        canvas.addGLEventListener(cube);

        frame.add(canvas);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
    }
}
