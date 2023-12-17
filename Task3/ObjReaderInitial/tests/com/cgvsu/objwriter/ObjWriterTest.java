package com.cgvsu.objwriter;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objwriter.ObjWriter;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjWriterTest {

    @Test
    void write() throws IOException {
        Model model = createSampleModel();
        String fileName = "test.obj";
        ObjWriter.write(fileName, model);
        assertTrue(new File(fileName).exists());
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        int vertexCount = 0;
        int textureVertexCount = 0;
        int normalCount = 0;
        int polygonCount = 0;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("v ")) {
                vertexCount++;
            } else if (line.startsWith("vt ")) {
                textureVertexCount++;
            } else if (line.startsWith("vn ")) {
                normalCount++;
            } else if (line.startsWith("f ")) {
                polygonCount++;
            }
        }

        reader.close();

        assertEquals(3, vertexCount);
        assertEquals(3, textureVertexCount);
        assertEquals(3, normalCount);
        assertEquals(1, polygonCount);
    }

    private Model createSampleModel() {
        Model model = new Model();
        Vector3f vertex1 = new Vector3f(1.0f, 2.0f, 3.0f);
        Vector3f vertex2 = new Vector3f(4.0f, 5.0f, 6.0f);
        Vector3f vertex3 = new Vector3f(7.0f, 8.0f, 9.0f);

        Vector2f textureVertex1 = new Vector2f(0.1f, 0.2f);
        Vector2f textureVertex2 = new Vector2f(0.3f, 0.4f);
        Vector2f textureVertex3 = new Vector2f(0.5f, 0.6f);

        Vector3f normal1 = new Vector3f(0.7f, 0.8f, 0.9f);
        Vector3f normal2 = new Vector3f(1.0f, 1.1f, 1.2f);
        Vector3f normal3 = new Vector3f(1.3f, 1.4f, 1.5f);

        model.vertices.add(vertex1);
        model.vertices.add(vertex2);
        model.vertices.add(vertex3);

        model.textureVertices.add(textureVertex1);
        model.textureVertices.add(textureVertex2);
        model.textureVertices.add(textureVertex3);

        model.normals.add(normal1);
        model.normals.add(normal2);
        model.normals.add(normal3);

        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(1, 2, 3)));
        polygon.setTextureVertexIndices(new ArrayList<>(Arrays.asList(1, 2, 3)));
        polygon.setNormalIndices(new ArrayList<>(Arrays.asList(1, 2, 3)));


        model.polygons.add(polygon);

        return model;
    }

}