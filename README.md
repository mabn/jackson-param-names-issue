# jackson-param-names-issue

When using ParameterNamesModule one would think that jackson will use the constructor. And it does.
Unfortunately afterwards it sets attributes for the second time using reflection. But only when contructor parameters do not match the PropertyNamingStrategy.
