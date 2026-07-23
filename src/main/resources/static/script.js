const elements = {
  forecastContainer: document.getElementById("forecast-container"),
  forecastGraph: document.getElementById("forecast-graph"),
  searchInput: document.getElementById("search-input"),
  searchButton: document.getElementById("search-button"),
  cityName: document.getElementById("city-name"),
  currentDate: document.getElementById("current-date"),
  temperature: document.getElementById("temperature"),
  tempRange: document.getElementById("temp-range"),
  condition: document.getElementById("condition"),
  weatherDescription: document.getElementById("weather-description"),
  humidity: document.getElementById("humidity"),
  wind: document.getElementById("wind"),
  pressure: document.getElementById("pressure"),
  feelsLike: document.getElementById("feels-like"),
  sidebarTemperature: document.getElementById("sidebar-temperature"),
  sidebarLocation: document.getElementById("sidebar-location"),
  sidebarHumidity: document.getElementById("sidebar-humidity"),
  sidebarWind: document.getElementById("sidebar-wind"),
  sidebarPressure: document.getElementById("sidebar-pressure"),
  sidebarVisibility: document.getElementById("sidebar-visibility"),
  selectedCity: document.getElementById("selected-city"),
  forecastContainer: document.getElementById("forecast-container"),
  statusText: document.getElementById("status-text"),
  weatherChip1: document.getElementById("weather-chip-1"),
  weatherChip2: document.getElementById("weather-chip-2"),
};

const forecastTemplate = [
  { day: "Mon", icon: "fa-cloud-sun", temp: "24°" },
  { day: "Tue", icon: "fa-cloud-showers-heavy", temp: "26°" },
  { day: "Wed", icon: "fa-cloud", temp: "29°" },
  { day: "Thu", icon: "fa-cloud-rain", temp: "25°" },
  { day: "Fri", icon: "fa-sun", temp: "28°" },
];

const themeMap = {
  sunny: "weather-sunny",
  clear: "weather-sunny",
  cloudy: "weather-cloudy",
  rain: "weather-rainy",
  rainy: "weather-rainy",
  night: "weather-night",
  winter: "weather-winter",
  morning: "weather-morning",
};

const weatherData = {
  location: "Jakarta",
  country: "Indonesia",
  temperature: "27°C",
  range: "H 31° • L 24°",
  condition: "sunny",
  description: "A light breeze passes through the high-rise city, keeping temperatures mild and comfortable.",
  humidity: "66%",
  wind: "12 km/h",
  pressure: "1016 hPa",
  feelsLike: "29°C",
  visibility: "9 km",
  status: "Sunny",
  chip1: "Cloudy",
  chip2: "7% chance of rain",
};

function formatDate() {
  const options = { weekday: "long", month: "long", day: "numeric" };
  return new Date().toLocaleDateString(undefined, options);
}

function applyWeatherTheme(condition = "sunny") {
  const themeClass = themeMap[condition.toLowerCase()] || "weather-sunny";
  document.body.className = themeClass;
}

function renderForecast(items) {
  if (!elements.forecastContainer) return;
  elements.forecastContainer.innerHTML = items
    .map(
      (item, index) => `
      <li class="forecast-item ${index === 2 ? "active" : ""}">
        <span>${item.day}</span>
        <i class="fa-solid ${item.icon}"></i>
        <span>${item.temp}</span>
      </li>`
    )
    .join("");
}
function renderForecastGraph(items) {
  if (!elements.forecastGraph) return;

  elements.forecastGraph.innerHTML = items
    .map((item, index) => {
      const date = new Date(item.date);

      const day = date.toLocaleDateString("en-US", {
        weekday: "short"
      });

      const temp = Math.round(item.maxTemperature);

      return `
        <div class="graph-column">
          <span>${day}</span>
          <div class="graph-bar ${index === 2 ? "active" : ""}"></div>
          <strong>${temp}°</strong>
        </div>
      `;
    })
    .join("");
}

function renderDashboard(data) {
  elements.cityName.textContent = data.location;
  elements.currentDate.textContent = formatDate();
  elements.temperature.textContent = data.temperature;
  elements.tempRange.textContent = data.range;
  elements.condition.textContent = data.condition;
  elements.weatherDescription.textContent = data.description;
  elements.humidity.textContent = data.humidity;
  elements.wind.textContent = data.wind;
  elements.pressure.textContent = data.pressure;
  elements.feelsLike.textContent = data.feelsLike;
  elements.sidebarTemperature.textContent = data.temperature;
  elements.sidebarLocation.textContent = `${data.location}, ${data.country}`;
  elements.sidebarHumidity.textContent = data.humidity;
  elements.sidebarWind.textContent = data.wind;
  elements.sidebarPressure.textContent = data.pressure;
  elements.sidebarVisibility.textContent = data.visibility;
  elements.selectedCity.textContent = data.location;
  elements.statusText.textContent = data.status || "Sunny";
  elements.weatherChip1.textContent = data.chip1 || "Cloudy";
  elements.weatherChip2.textContent = data.chip2 || "7% chance of rain";

  applyWeatherTheme(data.condition);
  renderForecast(forecastTemplate);
}

function showSearchFeedback(value) {
  if (!value) return;

  const mockData = {
    ...weatherData,
    location: value,
    condition: "rainy",
    status: "Rainy",
    chip1: "Shower",
    chip2: "60% chance of rain",
    description: `Live weather details for ${value} are ready to connect with your backend.`,
  };

  renderDashboard(mockData);
}
function getWeatherIcon(description) {
  const weather = description.toLowerCase();

  if (weather.includes("rain")) {
    return "fa-cloud-rain";
  }

  if (weather.includes("cloud")) {
    return "fa-cloud";
  }

  if (weather.includes("clear")) {
    return "fa-sun";
  }

  if (weather.includes("snow")) {
    return "fa-snowflake";
  }

  if (weather.includes("thunder")) {
    return "fa-cloud-bolt";
  }

  return "fa-cloud-sun";
}
async function handleSearch() {
  const city = elements.searchInput.value.trim();

  if (!city) {
    elements.searchInput.focus();
    return;
  }

  const response = await fetch(`/weather?city=${encodeURIComponent(city)}`);

  const data = await response.json();

  elements.cityName.textContent = data.city;
  elements.selectedCity.textContent = data.city;
  elements.sidebarLocation.textContent = data.city;

  elements.temperature.textContent = `${data.temperature}°C`;
  elements.sidebarTemperature.textContent = `${data.temperature}°C`;

  elements.humidity.textContent = `${data.humidity}%`;
  elements.sidebarHumidity.textContent = `${data.humidity}%`;

  elements.condition.textContent = data.description;
  elements.statusText.textContent = data.description;
  elements.weatherChip1.textContent = data.description;

  const forecastResponse = await fetch(
    `/forecast?city=${encodeURIComponent(city)}`
  );

  const forecastData = await forecastResponse.json();

  const formattedForecast = forecastData.map(item => {
    const date = new Date(item.date);

    return {
      day: date.toLocaleDateString("en-US", { weekday: "short" }),
      icon: getWeatherIcon(item.description),
      temp: `${Math.round(item.maxTemperature)}°`
    };
  });

  renderForecast(formattedForecast);
  renderForecastGraph(forecastData);
}

function init() {
  if (elements.searchButton) {
    elements.searchButton.addEventListener("click", handleSearch);
  }

  if (elements.searchInput) {
    elements.searchInput.addEventListener("keydown", (event) => {
      if (event.key === "Enter") {
        handleSearch();
      }
    });
  }

  renderDashboard(weatherData);
}

window.addEventListener("DOMContentLoaded", init);