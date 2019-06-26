package com.sjcdjsq.libs.utils;


import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 *rxjava异步
 */
public abstract class CtAsyncTask<Param, Progress, Result> {

    private Flowable<Progress[]> mFlowable;

    public CtAsyncTask() {
    }

    @SafeVarargs
    private final void rxTask(final Param... params) {
        Flowable.create(new FlowableOnSubscribe<Result>() {
            @Override
            public void subscribe(FlowableEmitter<Result> e) throws Exception {
                e.onNext(CtAsyncTask.this.call(params));
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mResultResourceSubscriber);
    }

    ResourceSubscriber<Result> mResultResourceSubscriber = new ResourceSubscriber<Result>() {


        @Override
        public void onNext(Result result) {
            CtAsyncTask.this.onResult(result);
        }

        @Override
        public void onError(Throwable t) {
            CtAsyncTask.this.onError(t);
        }

        @Override
        public void onComplete() {
            CtAsyncTask.this.onCompleted();
        }
    };

    protected abstract Result call(Param... params);

    protected void onStart() {
    }

    protected void onResult(Result result) {
    }

    protected void onProgressUpdate(Progress... progresses) {
    }

    protected void onCompleted() {
    }

    protected void onError(Throwable e) {
    }

    protected void updateProgress(final Progress... progresses) {
        if (mFlowable == null) {
            mFlowable = Flowable.create(new FlowableOnSubscribe<Progress[]>() {
                @Override
                public void subscribe(FlowableEmitter<Progress[]> e) throws Exception {
                    e.onNext(progresses);
                }
            }, BackpressureStrategy.BUFFER).observeOn(AndroidSchedulers.mainThread());
        }

        mFlowable.subscribe(new Consumer<Progress[]>() {
            @Override
            public void accept(Progress[] progress) throws Exception {
                onProgressUpdate(progresses);
            }
        });

    }

    @SafeVarargs
    public final void execute(Param... params) {
        rxTask(params);
    }
}
