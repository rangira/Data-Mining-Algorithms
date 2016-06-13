source("WeatherData.R")

#Function to calculate mean
bs.mean <- function(v) { sum(v)/length(v) }

# mean demonstration
bs.mean(weatherc$temperature)
mean(weatherc$temperature)

#Function to calculate weighted mean
bs.weighted.mean <- function(v, w=rep(1, length(v))) { sum(w*v)/sum(w) }
# demonstration
bs.weighted.mean(weatherc$temperature, ifelse(weatherc$play=="yes", 5, 1))
weighted.mean(weatherc$temperature, ifelse(weatherc$play=="yes", 5, 1))

#Function to calculate median
bs.median <- function(v)
{
  k1 <- (m <- length(v))%/%2+1
  k2 <- (m+1)%/%2
  ((v <- sort(v))[k1]+v[k2])/2
}
# demonstration
bs.median(weatherc$temperature)
bs.median(weatherc$temperature[weatherc$play=="yes"])
median(weatherc$temperature)
median(weatherc$temperature[weatherc$play=="yes"])

#Function to calculate variance
bs.var <- function(v) { sum((v-mean(v))^2)/(length(v)-1) }
# demonstration
bs.var(weatherr$playability)
var(weatherr$playability)


#Function to calculate Mode
weighted.modal <- function(v, w=rep(1, length(v)))
{
  m <- which.max(table(v, w=w))
  if (is.factor(v))
    factor(levels(v)[m], levels=levels(v))
  else
    sort(unique(v))[m]
}
# demonstration
weighted.modal(weather$outlook)
weighted.modal(weather$outlook, w=ifelse(weather$play=="yes", 2, 1))