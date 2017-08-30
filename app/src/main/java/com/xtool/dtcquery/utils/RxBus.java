package com.xtool.dtcquery.utils;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by xtool on 2017/8/29.
 */

public class RxBus {
    private static volatile RxBus mInstance;
    private final Subject<Object> subject = PublishSubject.create().toSerialized();
    private Disposable disposable;

    private RxBus() {

    }

    public static RxBus getInstance() {
        if(mInstance == null) {
            synchronized(RxBus.class) {
                if(mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送数据
     * @param object
     */
    public void send(Object object) {
        subject.onNext(object);
    }

    public <T> Observable<T> tObservable(Class<T> classType) {
        return subject.ofType(classType);
    }

    /**
     * 订阅
     * @param bean
     * @param consumer
     */
    public void subscribe(Class bean, Consumer consumer) {
        disposable = tObservable(bean).subscribe(consumer);
    }

    /**
     * 取消订阅
     */
    public void unSubscribe() {
        if(disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
