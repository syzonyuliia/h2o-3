from builtins import range
import sys
sys.path.insert(1,"../../../")
import h2o
from tests import pyunit_utils
from h2o.estimators.glm import H2OGeneralizedLinearEstimator as glm

# Verify scoring history generation for binomial with IRLSM, lambda_search, cross-validation, validation dataset and
# generate_scoring_history
def test_cv_alpha_lambda():
    h2o_data = h2o.import_file(path=pyunit_utils.locate("smalldata/glm_test/binomial_20_cols_10KRows.csv"))
    for ind in range(10):
        h2o_data[ind] = h2o_data[ind].asfactor()
    h2o_data["C21"] = h2o_data["C21"].asfactor()
    splits_frames = h2o_data.split_frame(ratios=[.8], seed=1234)
    train = splits_frames[0]
    valid = splits_frames[1]
    Y = "C21"
    X = list(range(0,20))

    print("Building model with score_interval=1 and lambda search on.  Should generate same model as "
          "score_each_iteration turned on.")
    h2o_model = glm(family="binomial", score_iteration_interval=1, lambda_search=True,
                    generate_scoring_history=True, alpha = [0.5, 0.0, 0.9], nfolds=3)
    h2o_model.train(x=X, y=Y, training_frame=train, validation_frame=valid)


if __name__ == "__main__":
    pyunit_utils.standalone_test(test_cv_alpha_lambda)
else:
    test_cv_alpha_lambda()
