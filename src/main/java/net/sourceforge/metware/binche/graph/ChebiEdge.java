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

public class ChebiEdge {

    private int id;
    private double pValue;

    public ChebiEdge(int id, double pValue) {

        this.id = id;
        this.pValue = pValue;
    }

    public int getId() {

        return id;
    }

    public double getpValue() {

        return pValue;
    }

    public String toString() {

        return String.format("%6.2e", pValue);
    }
}
