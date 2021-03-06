package free;

public abstract class Either<A, B>{
  private Either(){}

  public abstract <X> X fold(F1<A, X> left, F1<B, X> right);

  public boolean isRight(){
    return this instanceof Right;
  }

  public boolean isLeft(){
    return this instanceof Left;
  }

  final B rightOrNull(){
    if(isRight()){
      return ((Right<A, B>) this).value;
    }else {
      return null;
    }
  }

  final A leftOrNull(){
    if(isLeft()){
      return ((Left<A, B>) this).value;
    }else {
      return null;
    }
  }

  public static <X, Y> Either<X, Y> left(final X x){
    return new Left<>(x);
  }

  public static <X, Y> Either<X, Y> right(final Y y){
    return new Right<>(y);
  }

  private final static class Left<X, Y> extends Either<X, Y>{
    private final X value;
    private Left(final X value) {
      this.value = value;
    }

    @Override
    public <X1> X1 fold(F1<X, X1> left, F1<Y, X1> right) {
      return left.apply(value);
    }
  }

  private final static class Right<X, Y> extends Either<X, Y>{
    private final Y value;
    private Right(final Y value) {
      this.value = value;
    }

    @Override
    public <X1> X1 fold(F1<X, X1> left, F1<Y, X1> right) {
      return right.apply(value);
    }
  }
}
