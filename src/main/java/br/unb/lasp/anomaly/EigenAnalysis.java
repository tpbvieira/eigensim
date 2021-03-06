package br.unb.lasp.anomaly;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import br.unb.lasp.matrix.MatrixUtil;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.ojalgo.matrix.BasicMatrix.Factory;
import org.ojalgo.matrix.PrimitiveMatrix;

/**
 * @author thiago
 *
 */
public class EigenAnalysis{

	public static EigenvalueDecomposition eigencorrelation(Matrix matrix){

		int rowSize = matrix.getRowDimension();
		int columnSize = matrix.getColumnDimension();
		double[][] eigCor = new double[rowSize][columnSize];

		Mean mean = new Mean();
		StandardDeviation std = new StandardDeviation(false);// false for population standard deviation
		RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix.getArray());
		for(int line = 0; line < rowSize; line++){
			double[] row = realMatrix.getRow(line);			
			double rowStd = std.evaluate(row);			
			if(rowStd > 0){
				double rowMean = mean.evaluate(row);
				for(int column = 0; column < columnSize; column++){
					eigCor[line][column] = (row[column] - rowMean)/rowStd;
				}
			}
		}

		// EstimatedCorrelationMatrix
		Matrix eigCorMatrix = new Matrix(eigCor);
		eigCorMatrix = eigCorMatrix.times(1D/Double.valueOf(columnSize));
		final Factory<PrimitiveMatrix> tmpFactory = PrimitiveMatrix.FACTORY;
		PrimitiveMatrix pMatrix = tmpFactory.rows(eigCor).conjugate();
		Matrix conjTranspose = MatrixUtil.primitiveMatrixToJamaMatrix(pMatrix);
		eigCorMatrix = eigCorMatrix.times(conjTranspose);		

		return eigCorMatrix.eig();
	}

	public static EigenvalueDecomposition eigencovariance(Matrix matrix){
		
		int rowSize = matrix.getRowDimension();
		int columnSize = matrix.getColumnDimension();
		double[][] eigCor = new double[rowSize][columnSize];

		Mean mean = new Mean();
		RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix.getArray());
		for(int line = 0; line < rowSize; line++){
			double[] row = realMatrix.getRow(line);			
			double rowMean = mean.evaluate(row);
			for(int column = 0; column < columnSize; column++){
				eigCor[line][column] = row[column] - rowMean;
			}			
		}
		
		// EstimatedCorrelationMatrix
		Matrix eigCorMatrix = new Matrix(eigCor);
		eigCorMatrix = eigCorMatrix.times(1D/Double.valueOf(columnSize));
		final Factory<PrimitiveMatrix> tmpFactory = PrimitiveMatrix.FACTORY;
		PrimitiveMatrix pMatrix = tmpFactory.rows(eigCor).conjugate();
		Matrix conjTranspose = MatrixUtil.primitiveMatrixToJamaMatrix(pMatrix);
		eigCorMatrix = eigCorMatrix.times(conjTranspose);		
		
		return eigCorMatrix.eig();
	}

}