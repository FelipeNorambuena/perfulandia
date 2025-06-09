-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-06-2025 a las 03:58:19
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `perfulandia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL,
  `rut` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `id_usuario`, `nombre_completo`, `rut`, `direccion`, `telefono`, `email`) VALUES
(3, 8, 'Juan Bustos', '12.345.678-9', 'Av. Siempre Viva 123', '+56911112222', NULL),
(4, 13, 'Cliente Uno', '11.111.111-1', 'Calle Falsa 123', '+56911110001', NULL),
(5, 14, 'Cliente Dos', '22.222.222-2', 'Calle Falsa 124', '+56911110002', NULL),
(6, 15, 'Cliente Tres', '33.333.333-3', 'Calle Falsa 125', '+56911110003', NULL),
(7, 16, 'Cliente Cuatro', '44.444.444-4', 'Calle Falsa 126', '+56911110004', NULL),
(8, 17, 'Cliente Cinco', '55.555.555-5', 'Calle Falsa 127', '+56911110005', NULL),
(9, 18, 'Cliente Seis', '66.666.666-6', 'Calle Falsa 128', '+56911110006', NULL),
(10, 19, 'Cliente Siete', '77.777.777-7', 'Calle Falsa 129', '+56911110007', NULL),
(11, 20, 'Cliente Ocho', '88.888.888-8', 'Calle Falsa 130', '+56911110008', NULL),
(12, 21, 'Cliente Nueve', '99.999.999-9', 'Calle Falsa 131', '+56911110009', NULL),
(14, 34, 'divino cliente', '11.111.111-1', 'Av. Siempre divo 123', '+56911112222', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cupones`
--

CREATE TABLE `cupones` (
  `id_descuento` int(10) NOT NULL,
  `codigo` varchar(30) DEFAULT NULL,
  `descuento` int(5) NOT NULL,
  `valido_hasta` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventa`
--

CREATE TABLE `detalleventa` (
  `id_detalle` int(11) NOT NULL,
  `id_venta` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envios`
--

CREATE TABLE `envios` (
  `id_envio` int(11) NOT NULL,
  `id_venta` int(11) DEFAULT NULL,
  `direccion_envio` varchar(255) DEFAULT NULL,
  `estado_envio` varchar(255) DEFAULT NULL,
  `fecha_envio` date DEFAULT NULL,
  `fecha_entrega` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `envios`
--

INSERT INTO `envios` (`id_envio`, `id_venta`, `direccion_envio`, `estado_envio`, `fecha_envio`, `fecha_entrega`) VALUES
(7, 2, 'Calle Los Álamos 456, Concepción', 'entregado', '2025-06-06', '2025-06-08');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_clientes`
--

CREATE TABLE `historial_clientes` (
  `id_historial` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_compras`
--

CREATE TABLE `historial_compras` (
  `id_historial` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_vendedor` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `id_inventario` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `stock_disponible` int(11) DEFAULT NULL,
  `ubicacion_bodega` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`id_inventario`, `id_producto`, `stock_disponible`, `ubicacion_bodega`) VALUES
(1, 4, 500, 'Concepcion'),
(2, 5, 500, 'Concepcion'),
(3, 6, 350, 'Santiago'),
(4, 7, 400, 'Viña del Mar'),
(5, 8, 300, 'Concepcion'),
(6, 9, 450, 'Santiago'),
(7, 10, 380, 'Viña del Mar'),
(8, 11, 420, 'Concepcion'),
(9, 12, 500, 'Santiago'),
(10, 13, 350, 'Viña del Mar'),
(11, 14, 370, 'Concepcion'),
(12, 15, 420, 'Santiago'),
(13, 16, 390, 'Viña del Mar'),
(14, 17, 330, 'Concepcion'),
(15, 18, 460, 'Santiago'),
(16, 19, 410, 'Viña del Mar'),
(17, 20, 490, 'Concepcion'),
(18, 21, 320, 'Santiago'),
(19, 22, 350, 'Viña del Mar'),
(20, 23, 270, 'Concepcion'),
(21, 24, 310, 'Santiago'),
(22, 25, 290, 'Viña del Mar'),
(23, 26, 230, 'Concepcion'),
(24, 27, 340, 'Santiago'),
(25, 28, 280, 'Viña del Mar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio_unitario` double DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `nombre`, `descripcion`, `precio_unitario`, `categoria`, `activo`) VALUES
(4, 'Dolce & Gabbana Light Blue Pour Homme EDT', 'Fragancia masculina fresca y vibrante, inspirada en el espíritu del Mediterráneo. Combina notas cítricas y amaderadas para una sensación limpia, energética y sofisticada. Ideal para el uso diario en climas cálidos o actividades informales.', 61990, 'Fragancia masculina', 1),
(5, 'Dior Sauvage Parfum', 'Aroma masculino intenso y sofisticado, con notas de bergamota, vainilla y madera.', 89990, 'Fragancia masculina', 1),
(6, 'Acqua Di Gio Profumo', 'Fragancia elegante con notas marinas, incienso y pachuli. Ideal para la noche.', 84990, 'Fragancia masculina', 1),
(7, 'Bleu de Chanel', 'Notas amaderadas y cítricas. Aroma fresco y masculino para uso diario o de oficina.', 79990, 'Fragancia masculina', 1),
(8, 'Invictus Paco Rabanne', 'Notas frescas con toques dulces y amaderados. Perfume moderno y juvenil.', 69990, 'Fragancia masculina', 1),
(9, 'Dolce & Gabbana The One for Men', 'Fragancia cálida y especiada. Elegancia clásica para hombres sofisticados.', 65990, 'Fragancia masculina', 1),
(10, 'Chanel Coco Mademoiselle', 'Elegante fragancia femenina con notas florales y toques de ámbar y vainilla.', 94990, 'Fragancia femenina', 1),
(11, 'Lancôme La Vie Est Belle', 'Dulce y floral, esta fragancia es sinónimo de feminidad y libertad.', 84990, 'Fragancia femenina', 1),
(12, 'Carolina Herrera Good Girl', 'Seductora mezcla de flores blancas y cacao. Sensual y poderosa.', 81990, 'Fragancia femenina', 1),
(13, 'YSL Black Opium', 'Aroma adictivo de café, vainilla y flores blancas. Ideal para la noche.', 78990, 'Fragancia femenina', 1),
(14, 'Escada Flor del Sol', 'Fragancia veraniega y frutal, con notas de granada y cítricos tropicales.', 59990, 'Fragancia femenina', 1),
(15, 'Tom Ford Neroli Portofino', 'Fragancia cítrica y sofisticada, ideal para cualquier género. Inspirada en la costa italiana.', 112990, 'Fragancia unisex', 1),
(16, 'CK One', 'Clásica fragancia fresca, ligera y unisex. Perfecta para uso diario.', 48990, 'Fragancia unisex', 1),
(17, 'Maison Margiela Lazy Sunday Morning', 'Aroma suave a flores blancas y almizcle, unisex y reconfortante.', 87990, 'Fragancia unisex', 1),
(18, 'Byredo Blanche', 'Fragancia minimalista, limpia y floral. Ideal para quienes buscan sutileza.', 98990, 'Fragancia unisex', 1),
(19, 'Acqua di Parma Colonia', 'Cítrica, ligera y clásica. Unisex, con gran frescura.', 65990, 'Fragancia unisex', 1),
(20, 'Victoria’s Secret Pure Seduction', 'Bruma corporal frutal con ciruela roja y fresia. Ligera y juvenil.', 10990, 'Body Mist', 1),
(21, 'Bath & Body Works Gingham', 'Bruma fresca con notas cítricas y florales. Ideal para después de la ducha.', 17990, 'Body Mist', 1),
(22, 'Victoria’s Secret Love Spell', 'Bruma con mezcla de cereza, durazno y flor de azahar. Dulce y romántica.', 19990, 'Body Mist', 1),
(23, 'Bath & Body Works Into the Night', 'Bruma intensa con grosellas negras y ámbar. Ideal para salidas nocturnas.', 18990, 'Body Mist', 1),
(24, 'Ariana Grande Cloud Body Mist', 'Aroma ligero de crema batida, coco y lavanda. Versión bruma del famoso perfume.', 17990, 'Body Mist', 1),
(25, 'Miniatura Dior Homme Sport', 'Miniatura de 10 ml ideal para viajes. Notas cítricas y frescas con toque especiado.', 11990, 'Miniatura', 1),
(26, 'Miniatura Versace Eros', 'Tamaño compacto de 5 ml. Fragancia intensa, masculina y elegante.', 9990, 'Miniatura', 1),
(27, 'Set de Regalo Calvin Klein Women', 'Incluye perfume 50 ml, crema corporal y miniatura. Ideal para regalar.', 65990, 'Set de regalo', 1),
(28, 'Set de Regalo Jean Paul Gaultier Scandal', 'Estuche con perfume, loción corporal y miniatura. Edición especial.', 75990, 'Set de regalo', 1),
(30, 'esika', 'fragancia de la alta alcurnia', 5990, 'macho', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `id_reporte` int(11) NOT NULL,
  `tipo_reporte` varchar(255) DEFAULT NULL,
  `fecha_generacion` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `json_datos` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `soporte`
--

CREATE TABLE `soporte` (
  `id_ticket` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `tipo_ticket` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `fecha_resolucion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre_usuario` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `contraseña` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre_usuario`, `email`, `contrasena`, `rol`, `estado`, `contraseña`) VALUES
(8, 'juanbustos', 'nuevo@email.com', '$2a$10$lt154kkMDd1fR7Ak6ql5v.YsGzGRVrZcURoUnsUZE1bkGMrIgZSwi', 'cliente', 'activo', NULL),
(10, 'admin', 'admin01@ejemplo.com', '$2a$10$8j2oZXxk8hEfNtjzs7tx7./hodZx0BC27cP4WbZIpMDEYfij4CRHi', 'admin', 'activo', NULL),
(11, 'admin01', 'admin01@ejemplo.com', '$2a$10$V3yg9OTQxQJQuOQHx8jSeegoN5nJaaozqQwdQQnJRdyOP.6Aj2/Me', 'admin', 'activo', NULL),
(12, 'vendedor01', 'vendedor01@ejemplo.com', '$2a$10$15NtyP1URZUvu5LT2wGzZehRtLp9J.9X00VLQMYuA5deGC1dzXNPy', 'vendedor', 'activo', NULL),
(13, 'cliente1', 'cliente1@example.com', '$2a$10$KgOR5iaHvpgIL.JFAHwsWeCe9d3qS4co3fqXmdjMsTl6fEPA1VXei', 'cliente', 'activo', NULL),
(14, 'cliente2', 'cliente2@example.com', '$2a$10$XmGEuP/u5/hCu1XmpTJNLuFplkwHE.D6.lWQhuluNGRKEWFN4wOnC', 'cliente', 'activo', NULL),
(15, 'cliente3', 'cliente3@example.com', '$2a$10$SzlsPx/6qT0g4FMYwkzutOp20v5ISzb0yMwhW0xZsFiIo3jIjc2UW', 'cliente', 'activo', NULL),
(16, 'cliente4', 'cliente4@example.com', '$2a$10$6TrjrBR.URogRIIT1pD6sOUM28VqIinUy/QFNsEuIvOWiI9CON2pu', 'cliente', 'activo', NULL),
(17, 'cliente5', 'cliente5@example.com', '$2a$10$PHwWstVniUw7cUqn7y3vMOaQ8/XZ0IeybydZPueE7XLGls9bdAf2G', 'cliente', 'activo', NULL),
(18, 'cliente6', 'cliente6@example.com', '$2a$10$PPBnYCsMGnyzMG6H1ljqX.1tgyem6/ORDBowK663.mMqXnXHuQ0fm', 'cliente', 'activo', NULL),
(19, 'cliente7', 'cliente7@example.com', '$2a$10$EdKZc7oi/1kliOCjdPrJA.d2S5OD4wxBSYElcwUjN/OKJ3VDEbXCu', 'cliente', 'activo', NULL),
(20, 'cliente8', 'cliente8@example.com', '$2a$10$KwMJn1EyiFU69tFa5uSXJ.BXR/pjuOLx0jm5CRAtohiX98qHxvFjG', 'cliente', 'activo', NULL),
(21, 'cliente9', 'cliente9@example.com', '$2a$10$juOJbJ3hWUWE.Z7.kG9A8euIuByBlcinz./FFcP3OOjwowkXUSzY.', 'cliente', 'activo', NULL),
(22, 'cliente10', 'cliente10@example.com', '$2a$10$teBHY4s3rGe0V2sACWOjXeMgxjMF069s8Gw2bCLIuk3FK3OG1hD5W', 'cliente', 'activo', NULL),
(23, 'admin02', 'admin02@ejemplo.com', '$2a$10$uB.NHk1sHd.M7/oGt9a41uWCh6AMX7.ZO3X2T51fhmbYV7ry1/GGu', 'admin', 'activo', NULL),
(24, 'admin03', 'admin03@ejemplo.com', '$2a$10$g55g7fz0kfgfeB0pDBx4euH2socvkXL6YH3oKIK5NVF/nXBwLNWNC', 'admin', 'activo', NULL),
(25, 'vendedor02', 'vendedor02@ejemplo.com', '$2a$10$M1p76T7gB2PXJIRc6QLBV.qeBVtVBzsLLRi.INnYiRAo1qmHm.Zza', 'vendedor', 'activo', NULL),
(26, 'vendedor03', 'vendedor03@ejemplo.com', '$2a$10$6m6pJ9BGuhwx.LU6n1WYqubuuP0LYkpexI.ZBNdnzyAU2oUVMZN5y', 'vendedor', 'activo', NULL),
(27, 'vendedor04', 'vendedor04@ejemplo.com', '$2a$10$YVF.zxZOUIWHi2mkzMJokuZFKId8Bm6fGPPC.j57kDtk7GTcUxrw2', 'vendedor', 'activo', NULL),
(28, 'vendedor05', 'vendedor05@ejemplo.com', '$2a$10$eMOuMGttNV5O6YUZvpKgEunw6FxU8lDXvrCf2zUpCImCNU4Ks3W4q', 'vendedor', 'activo', NULL),
(29, 'vendedor06', 'vendedor06@ejemplo.com', '$2a$10$oT.ZJOtH/keG9Jkrons/3eRyl2xZZDonkbo4A2H.Gc6AgClEgQLtG', 'vendedor', 'activo', NULL),
(30, 'vendedor07', 'vendedor07@ejemplo.com', '$2a$10$nNgZBmBBwkfNuiVU2BXlHexfAbnXk8LGD7C5bTrRPqx.Ky8tb/TGm', 'vendedor', 'activo', NULL),
(31, 'vendedor08', 'vendedor08@ejemplo.com', '$2a$10$/QH1VDQRLNrZJjnfs/QwH.OUsl9S7PeYnSHLT5ocxtOUGPpYykrI6', 'vendedor', 'activo', NULL),
(32, 'vendedor09', 'vendedor09@ejemplo.com', '$2a$10$tvrGP4tK9gfmJE57ZZFE6Od4YaPfHga1uJIa5eiC39EkUI/UQYr8q', 'vendedor', 'activo', NULL),
(33, 'vendedor10', 'vendedor10@ejemplo.com', '$2a$10$tukaPQQIj/4XbixW3sLQFeliTSeB45gkXc9Q4azlo9PCW0ptPqYhq', 'vendedor', 'activo', NULL),
(34, 'divino', 'eldivino@example.com', '$2a$10$Pa.mHOMCAnBFt/jPm2dIUug6OU7MtNlRjWy1OtY0llbdaEOMlwDe6', 'cliente', 'activo', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedores`
--

CREATE TABLE `vendedores` (
  `id_vendedor` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL,
  `rut` varchar(255) DEFAULT NULL,
  `area_ventas` varchar(255) DEFAULT NULL,
  `meta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vendedores`
--

INSERT INTO `vendedores` (`id_vendedor`, `id_usuario`, `nombre_completo`, `rut`, `area_ventas`, `meta`) VALUES
(4, 12, 'Camila Pérez', '11.223.334-5', 'Región Metropolitana', 0),
(5, 25, 'Jorge Morales', '12.345.678-9', 'santiago', 0),
(6, 26, 'Valentina Soto', '13.456.789-0', 'santiago', 0),
(7, 27, 'Ricardo Fuentes', '14.567.890-1', 'concepcion', 0),
(8, 28, 'Fernanda Reyes', '15.678.901-2', 'concepcion', 0),
(9, 29, 'Martín Vera', '16.789.012-3', 'concepcion', 0),
(10, 30, 'Catalina Rivas', '17.890.123-4', 'viña del mar', 0),
(11, 31, 'Andrés Paredes', '18.901.234-5', 'viña del mar', 5000),
(12, 32, 'Nicole Gutiérrez', '19.012.345-6', 'viña del mar', 0),
(13, 33, 'Benjamín Silva', '20.123.456-7', 'concepcion', 5000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id_venta` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_vendedor` int(11) DEFAULT NULL,
  `fecha_venta` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id_venta`, `id_cliente`, `id_vendedor`, `fecha_venta`) VALUES
(1, 3, 4, '2025-06-01 00:00:00.000000'),
(2, 4, 5, '2025-06-02 00:00:00.000000'),
(3, 5, 6, '2025-06-02 00:00:00.000000'),
(4, 6, 7, '2025-06-03 00:00:00.000000'),
(5, 7, 8, '2025-06-03 00:00:00.000000'),
(6, 8, 9, '2025-06-04 00:00:00.000000'),
(7, 9, 10, '2025-06-05 00:00:00.000000'),
(8, 10, 11, '2025-06-05 00:00:00.000000'),
(9, 11, 12, '2025-06-06 00:00:00.000000'),
(10, 12, 13, '2025-06-07 00:00:00.000000'),
(11, 5, 8, '2025-06-08 19:52:04.000000'),
(12, 3, 4, '2025-06-01 00:00:00.000000'),
(13, 4, 5, '2025-06-02 00:00:00.000000'),
(14, 5, 6, '2025-06-02 00:00:00.000000'),
(15, 6, 7, '2025-06-03 00:00:00.000000'),
(16, 7, 8, '2025-06-03 00:00:00.000000'),
(17, 8, 9, '2025-06-04 00:00:00.000000'),
(18, 9, 10, '2025-06-05 00:00:00.000000'),
(19, 10, 11, '2025-06-05 00:00:00.000000'),
(20, 11, 12, '2025-06-06 00:00:00.000000'),
(21, 12, 13, '2025-06-07 00:00:00.000000');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `clientes_ibfk_1` (`id_usuario`);

--
-- Indices de la tabla `cupones`
--
ALTER TABLE `cupones`
  ADD PRIMARY KEY (`id_descuento`);

--
-- Indices de la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `detalleventa_ibfk_1` (`id_venta`),
  ADD KEY `detalleventa_ibfk_2` (`id_producto`);

--
-- Indices de la tabla `envios`
--
ALTER TABLE `envios`
  ADD PRIMARY KEY (`id_envio`),
  ADD KEY `envios_ibfk_1` (`id_venta`);

--
-- Indices de la tabla `historial_clientes`
--
ALTER TABLE `historial_clientes`
  ADD PRIMARY KEY (`id_historial`);

--
-- Indices de la tabla `historial_compras`
--
ALTER TABLE `historial_compras`
  ADD PRIMARY KEY (`id_historial`),
  ADD KEY `idx_historial_cliente` (`id_cliente`),
  ADD KEY `idx_historial_producto` (`id_producto`),
  ADD KEY `idx_historial_vendedor` (`id_vendedor`),
  ADD KEY `idx_historial_venta` (`id_venta`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`id_inventario`),
  ADD KEY `inventario_ibfk_1` (`id_producto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD PRIMARY KEY (`id_reporte`);

--
-- Indices de la tabla `soporte`
--
ALTER TABLE `soporte`
  ADD PRIMARY KEY (`id_ticket`),
  ADD KEY `soporte_ibfk_1` (`id_usuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`id_vendedor`),
  ADD KEY `vendedores_ibfk_1` (`id_usuario`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `ventas_ibfk_1` (`id_cliente`),
  ADD KEY `ventas_ibfk_2` (`id_vendedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `cupones`
--
ALTER TABLE `cupones`
  MODIFY `id_descuento` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `envios`
--
ALTER TABLE `envios`
  MODIFY `id_envio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `historial_clientes`
--
ALTER TABLE `historial_clientes`
  MODIFY `id_historial` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `historial_compras`
--
ALTER TABLE `historial_compras`
  MODIFY `id_historial` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `id_inventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `reportes`
--
ALTER TABLE `reportes`
  MODIFY `id_reporte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `soporte`
--
ALTER TABLE `soporte`
  MODIFY `id_ticket` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  MODIFY `id_vendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD CONSTRAINT `detalleventa_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalleventa_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `envios`
--
ALTER TABLE `envios`
  ADD CONSTRAINT `envios_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `historial_compras`
--
ALTER TABLE `historial_compras`
  ADD CONSTRAINT `fk_historial_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_historial_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_historial_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedores` (`id_vendedor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_historial_venta` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `inventario_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `soporte`
--
ALTER TABLE `soporte`
  ADD CONSTRAINT `soporte_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD CONSTRAINT `vendedores_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedores` (`id_vendedor`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
