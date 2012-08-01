/*
 * Copyright (c) 2012, Stephan Beisken. All rights reserved.
 *
 * This file is part of BiNChe.
 *
 * BiNChe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BiNChe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BiNChe. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.metware.binche.graph;

import cytoscape.data.annotation.Ontology;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChebiGraph extends DirectedSparseMultigraph<ChebiVertex, ChebiEdge> {

    private static final Logger LOGGER = Logger.getLogger(ChebiGraph.class);

    private final static int WIDTH = 500;
    private final static int HEIGHT = 500;

    private Layout<ChebiVertex, ChebiEdge> layout;

    public ChebiGraph(HashMap<String, HashSet<String>> classifiedEntities, Map<Integer, Double> pValueMap,
                      Ontology ontology, HashSet<String> nodes) {

        super();

        // tmp hack
        HashSet<String> nodesMod = new HashSet<String>();
        for (String chebiId : nodes) {
            nodesMod.add(chebiId.split(":")[1]);
        }
        nodes = nodesMod;

        Map<Integer, ChebiVertex> vertexMap = new HashMap<Integer, ChebiVertex>();
        int vertexId = 0;
        int edgeId = 0;

        for (Map.Entry<String, HashSet<String>> e : classifiedEntities.entrySet()) {

            String key = e.getKey();
            Integer keyNum = new Integer(key);

            if (pValueMap.containsKey(keyNum)) {

                if (!vertexMap.containsKey(keyNum)) {
                    vertexMap.put(keyNum, new ChebiVertex(vertexId, key, ontology.getTerm(keyNum).getName()));
                    if (nodes.contains(key)) vertexMap.get(keyNum).setColor(Color.RED);
                    vertexId++;
                }

                for (String chebiId : e.getValue()) {
                    Integer id = new Integer(chebiId.split(":")[1]);

                    if (!vertexMap.containsKey(id)) {
                        vertexMap.put(id, new ChebiVertex(vertexId, "" + id, ontology.getTerm(id).getName()));
                        if (nodes.contains("" + id)) vertexMap.get(id).setColor(Color.RED);
                        vertexId++;
                    }

                    addEdge(new ChebiEdge(edgeId, pValueMap.get(keyNum)), vertexMap.get(id), vertexMap.get(keyNum),
                            EdgeType.DIRECTED);
                    edgeId++;
                }

//                LOGGER.log(Level.INFO, e);
//                LOGGER.log(Level.INFO, pValueMap.get(keyNum));
//                LOGGER.log(Level.INFO, ontology.getTerm(keyNum));
            }
        }

        layoutGraph();
    }

    private void layoutGraph() {

        layout = new ISOMLayout<ChebiVertex, ChebiEdge>(this);
        layout.setSize(new Dimension(WIDTH, HEIGHT));
    }

    public VisualizationViewer<ChebiVertex, ChebiEdge> getVisualizationViewer(int width, int height) {

        VisualizationViewer<ChebiVertex, ChebiEdge> bvs = new VisualizationViewer<ChebiVertex, ChebiEdge>(layout);
        bvs.setSize(new Dimension(width, height));

        setTransformer(bvs);
        setMouse(bvs);

        return bvs;
    }

    public VisualizationImageServer<ChebiVertex, ChebiEdge> getVisualisationServer(int width, int height) {

        VisualizationImageServer<ChebiVertex, ChebiEdge> vis =
                new VisualizationImageServer<ChebiVertex, ChebiEdge>(layout, new Dimension(width, height));
        setTransformer(vis);

        return vis;
    }

    private void setTransformer(BasicVisualizationServer<ChebiVertex, ChebiEdge> bvs) {

        bvs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<ChebiVertex>());
        bvs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<ChebiEdge>());
        bvs.getRenderContext().setVertexFillPaintTransformer(getVertexTransformer());
        bvs.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
    }

    private Transformer<ChebiVertex, Paint> getVertexTransformer() {

        Transformer<ChebiVertex, Paint> vertexPaint = new Transformer<ChebiVertex, Paint>() {

            public Paint transform(ChebiVertex vertex) {

                return vertex.getColor();
            }
        };

        return vertexPaint;
    }

    private void setMouse(VisualizationViewer<ChebiVertex, ChebiEdge> bvs) {

        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        bvs.setGraphMouse(gm);
    }
}
