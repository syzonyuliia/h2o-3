package hex.glm;

import hex.Model;
import hex.ModelBuilder;
import hex.genmodel.utils.DistributionFamily;
import org.junit.BeforeClass;
import org.junit.Test;
import water.MetricTest;
import water.Scope;
import water.fvec.Frame;

import java.util.function.Function;

public class GLMMetricTest extends MetricTest {
    
    private Function<Model.Parameters, ModelBuilder> glmConstructor =  parameters -> {
        GLMModel.GLMParameters glmParameters = (GLMModel.GLMParameters)parameters;
        return new GLM(glmParameters);
    };

    @BeforeClass
    public static void setup() { stall_till_cloudsize(1); }

    @Test
    public void testIndependentModelMetricsCalculation_regression() {
        Scope.enter();
        try {
            String response = "AGE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv", new int[]{0}));

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._distribution = DistributionFamily.gaussian;
            
            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }

    @Test
    public void testIndependentModelMetricsCalculation_binomial() {
        Scope.enter();
        try {
            String response = "CAPSULE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv", new int[]{0}));
            dataset.toCategoricalCol(response);

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._distribution = DistributionFamily.bernoulli;
            
            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }

    @Test
    public void testIndependentModelMetricsCalculation_multinomial() {
        Scope.enter();
        try {
            String response = "AGE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv", new int[]{0}));
            dataset.toCategoricalCol(response);

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._distribution = DistributionFamily.multinomial;
            
            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }
    
    @Test
    public void testIndependentModelMetricsCalculation_ordinal() {
        Scope.enter();
        try {
            String response = "AGE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv", new int[]{0}));
            dataset.toCategoricalCol(response);

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._family = GLMModel.GLMParameters.Family.ordinal;

            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }

    @Test
    public void testIndependentModelMetricsCalculationWithOffsetColumn_regression() {
        Scope.enter();
        try {
            String response = "AGE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv"));

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._distribution = DistributionFamily.gaussian;
            params._offset_column = "ID";

            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }

    @Test
    public void testIndependentModelMetricsCalculationWithOffsetColumn_binomial() {
        Scope.enter();
        try {
            String response = "CAPSULE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv"));
            dataset.toCategoricalCol(response);

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._distribution = DistributionFamily.bernoulli;
            params._offset_column = "ID";

            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }

    @Test
    public void testIndependentModelMetricsCalculationWithOffsetColumn_multinomial() {
        Scope.enter();
        try {
            String response = "AGE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv"));
            dataset.toCategoricalCol(response);

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._distribution = DistributionFamily.multinomial;
            params._offset_column = "ID";

            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }

    @Test
    public void testIndependentModelMetricsCalculationWithWeightColumn_regression() {
        Scope.enter();
        try {
            String response = "AGE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv"));

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._distribution = DistributionFamily.gaussian;
            params._weights_column = "ID";

            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }

    @Test
    public void testIndependentModelMetricsCalculationWithWeightColumn_binomial() {
        Scope.enter();
        try {
            String response = "CAPSULE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv"));
            dataset.toCategoricalCol(response);

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._distribution = DistributionFamily.bernoulli;
            params._weights_column = "ID";

            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }

    @Test
    public void testIndependentModelMetricsCalculationWithWeightColumn_multinomial() {
        Scope.enter();
        try {
            String response = "AGE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv"));
            dataset.toCategoricalCol(response);

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._distribution = DistributionFamily.multinomial;
            params._weights_column = "ID";

            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }

    @Test
    public void testIndependentModelMetricsCalculationWithWeightColumn_ordinal() {
        Scope.enter();
        try {
            String response = "AGE";
            Frame dataset = Scope.track(parseTestFile("smalldata/prostate/prostate.csv"));
            dataset.toCategoricalCol(response);

            GLMModel.GLMParameters params = new GLMModel.GLMParameters();
            params._response_column = response;
            params._family = GLMModel.GLMParameters.Family.ordinal;
            params._weights_column = "ID";

            double tolerance = 0.000001;
            testIndependentlyCalculatedSupervisedMetrics(dataset, params, glmConstructor, tolerance);
        } finally {
            Scope.exit();
        }
    }
}