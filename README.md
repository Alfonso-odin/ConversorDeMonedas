# Conversor de Monedas y Criptomonedas 💱💰

Un proyecto en Java que permite convertir valores entre diferentes **monedas tradicionales** y **criptomonedas**, utilizando tasas de cambio en tiempo real obtenidas desde una API.

## ✨ Características

- Conversión entre monedas tradicionales: USD, EUR, MXN, etc.
- Conversión entre criptomonedas: BTC, ETH, USDT, y más
- Soporte para tasas de cambio en tiempo real (mediante API REST)
- Interfaz sencilla y amigable (CLI o GUI)
- Código limpio, modular y extensible

## 🌐 API Utilizada

Este proyecto se conecta a una API de tipo de cambio en tiempo real:

- [CoinGecko API](https://www.coingecko.com/) (para criptos)
- [Api Keys] (https://www.exchangerate-api.com/) para divisas

> Asegúrate de configurar tu propia API key en el código si la API lo requiere.

## 🛠️ Tecnologías

- Java 8 o superior
- Librerías HTTP (`HttpURLConnection`, `HttpClient`, o `OkHttp`)
- JSON parser (`org.json`, `Gson` o `Jackson`)

## 📁 Estructura del Proyecto

```
Conversor-De-Monedas/
├── .idea/
├── Lib/
├── out/
├── src/
│   ├── Config/                 # Clases de configuración
│   ├── config.properties       # Archivo de configuración con claves API, endpoints, etc.
│   ├── ConversorDeMonedas.java  # Clase principal
│   ├── ConversorMenu.java      # Menú interactivo
│   ├── MonedaDigital.java      # Conversión de criptomonedas
├── .gitignore
├── ConversorDeMonedas.iml
```

## ⚙️ Cómo ejecutar

1. Clona este repositorio:
   ```bash
   git clone https://github.com/Alfonso-odin/ConversorDeMonedas.git
   ```
2. Abre el proyecto en tu IDE (por ejemplo, IntelliJ IDEA).
3. Asegúrate de tener Java 11 o superior instalado.
4. Configura el archivo `config.properties` con tu clave de API y los endpoints necesarios.
5. Ejecuta la clase `ConversorDeMonedas`.


## 🧠 Autor

Proyecto desarrollado por **Alfonso Rendón**.

